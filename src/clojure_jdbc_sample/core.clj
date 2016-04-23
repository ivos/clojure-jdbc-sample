(ns clojure-jdbc-sample.core
  (:gen-class)
  (:require [clojure-jdbc-sample.customer-repo :as repo]
            [clojure-jdbc-sample.migration :as migration]))

(migration/clean)
(migration/migrate)

(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "h2:file"
   :subname "./target/db/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE"
   :user "sa"
   :password ""})

(defn -main [& args]
  (let [alpha1 (repo/create-customer db-spec {:name "Alpha"})
        alpha2 (repo/update-customer db-spec (assoc alpha1 :email "info@alpha.com"))
        alpha3 (repo/update-customer db-spec (assoc alpha2 :phone "+2-000-888-666"))]
    (repo/create-customer db-spec {:name "Beta" :email "info@beta.com" :phone "+1-234-555-678"})
    (repo/delete-customer db-spec alpha3)
  )
)
