(ns clojure-jdbc-sample.migration
  (import [org.flywaydb.core Flyway])
  (require [clojure-jdbc-sample.db-setup :refer :all]
           [clojure.java.jdbc :refer [db-do-commands]]))

(defn migrate []
  (let [flyway (doto (Flyway.)
		 (.setDataSource datasource)
		 (.setSqlMigrationPrefix ""))]
       (.migrate flyway)))

(defn clean []
  (let [flyway (doto (Flyway.)
		 (.setDataSource datasource)
		 (.setSqlMigrationPrefix ""))]
       (.clean flyway)))
