(ns clojure-jdbc-sample.db-setup
  (:require [hikari-cp.core :as ds]))

(def datasource-config
  {:adapter "h2"
   :url "jdbc:h2:file:./target/db/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE;TRACE_LEVEL_FILE=4"
   :username "sa"
   :password ""
   :maximum-pool-size 2
   :minimum-idle 2
  })

(def datasource
  (ds/make-datasource datasource-config))

(def db-spec
  {:datasource datasource
  })

(defn close-datasource []
  (ds/close-datasource datasource))
