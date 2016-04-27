(ns clojure-jdbc-sample.migration
  (import [org.flywaydb.core Flyway])
  (require [clojure-jdbc-sample.db-setup :refer [datasource]]))

(def flyway
  (doto (Flyway.)
    (.setDataSource datasource)
  	(.setSqlMigrationPrefix "")))

(defn migrate []
  (.migrate flyway))

(defn clean []
  (.clean flyway))
