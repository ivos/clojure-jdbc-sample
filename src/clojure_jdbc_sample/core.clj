(ns clojure-jdbc-sample.core
  (:gen-class)
  (:require [clojure-jdbc-sample.repo :as repo]
            [clojure-jdbc-sample.migration :as migration]
            [yesql.core :refer [defqueries]]))

(migration/clean)
(migration/migrate)

(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "h2:file"
   :subname "./target/db/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE;TRACE_LEVEL_FILE=4"
   :user "sa"
   :password ""})

(defqueries "db/sql/customer.sql"
  {:connection db-spec})

(defn -main [& args]
  (let [alpha1 (repo/create! db-spec :customer {:name "Alpha"})
        alpha2 (repo/update! db-spec :customer (assoc alpha1 :email "info@alpha.com"))
        alpha3 (repo/update! db-spec :customer (assoc alpha2 :phone "+2-000-888-666"))
        beta (repo/create! db-spec :customer {:name "Beta" :email "info@beta.com" :phone "+1-234-555-678"})
        all (list-all-customers)
        alpha4 (get-customer alpha1)
        alpha5 (repo/delete! db-spec :customer alpha3)
        after-delete (list-all-customers)]
    (println "All:" all)
    (println "Alpha4:" alpha4)
    (println "After delete:" after-delete)
  )
)
