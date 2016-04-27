(ns clojure-jdbc-sample.core
  (:gen-class)
  (:require [clojure-jdbc-sample.db-setup :refer [db-spec close-datasource]]
            [clojure-jdbc-sample.repo :as repo]
            [clojure-jdbc-sample.migration :as migration]
            [hugsql.core :refer [def-db-fns]]
  ))

(migration/clean)
(migration/migrate)

(def-db-fns "db/sql/customer.sql"
  {:connection db-spec})

(defn -main [& args]
  (let [alpha1 (repo/create! db-spec :customer {:name "Alpha"})
        alpha2 (repo/update! db-spec :customer (assoc alpha1 :email "info@alpha.com"))
        alpha3 (repo/update! db-spec :customer (assoc alpha2 :phone "+2-000-888-666"))
        beta (repo/create! db-spec :customer {:name "Beta" :email "info@beta.com" :phone "+1-234-555-678"})
        all (list-all-customers db-spec)
        alpha4 (get-customer db-spec alpha1)
        alpha5 (repo/delete! db-spec :customer alpha3)
        after-delete (list-all-customers db-spec)]
    (println "All:" all)
    (println "Alpha4:" alpha4)
    (println "After delete:" after-delete)
    (close-datasource)
  )
)
