(ns clojure-jdbc-sample.migration
  (import [org.flywaydb.core Flyway])
  (require [clojure.java.jdbc :refer [db-do-commands]]))

(defn migrate []
  (let [flyway (doto (Flyway.)
		 (.setDataSource "jdbc:h2:file:./target/db/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE" "sa" nil (into-array String []))
		 (.setSqlMigrationPrefix ""))]
       (.migrate flyway)))

(defn clean []
  (let [flyway (doto (Flyway.)
		 (.setDataSource "jdbc:h2:file:./target/db/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE" "sa" nil (into-array String []))
		 (.setSqlMigrationPrefix ""))]
       (.clean flyway)))
