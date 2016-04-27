(ns clojure-jdbc-sample.migration
  (import [org.flywaydb.core Flyway])
  (require [clojure-jdbc-sample.db-setup :refer :all]))

(def flyway
  (doto (Flyway.)
    (.setDataSource datasource)
  	(.setSqlMigrationPrefix "")))

(defn migrate []
  (.migrate flyway))

(defn clean []
  (.clean flyway))
