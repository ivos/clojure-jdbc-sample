(ns clojure-jdbc-sample.db-setup
  (:require [hikari-cp.core :as ds]))

(def datasource-config
  (read-string (slurp "resources/db/db-config.edn")))

(def datasource
  (ds/make-datasource datasource-config))

(def db-spec
  {:datasource datasource})

(defn close-datasource []
  (ds/close-datasource datasource))
