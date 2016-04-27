(ns clojure-jdbc-sample.core
  (:gen-class)
  (:require [clojure-jdbc-sample.db-setup :refer [db-spec close-datasource]]
            [clojure-jdbc-sample.repo :as repo]
            [clojure-jdbc-sample.migration :as migration]
            [clojure.java.jdbc :as db]
            [hugsql.core :refer [def-db-fns]]
  ))

(migration/clean)
(migration/migrate)

(def-db-fns "clojure_jdbc_sample/customer.sql"
  {:connection db-spec})

(defn setup-data []
  (db/with-db-transaction [tc db-spec]
    (let [alpha1 (repo/create! tc :customer {:name "Alpha"})
          alpha2 (repo/update! tc :customer (assoc alpha1 :email "info@alpha.com"))
          alpha3 (repo/update! tc :customer (assoc alpha2 :phone "+2-000-888-666"))
          beta (repo/create! tc :customer {:name "Beta" :email "info@beta.com" :phone "+1-234-555-678"})
;          _ (throw (RuntimeException. "Bla"))
          all (list-all-customers tc)
          alpha4 (get-customer tc alpha1)
          alpha5 (repo/delete! tc :customer alpha3)
          after-delete (list-all-customers tc)]
      (println "All:" all)
      (println "Alpha4:" alpha4)
      (println "After delete:" after-delete)
  )))

(defn -main [& args]
  (setup-data)
  (close-datasource)
)
