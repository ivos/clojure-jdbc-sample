(ns clojure-jdbc-sample.core
  (:gen-class)
  (:require [clojure.java.jdbc :as db]))

(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "h2:file"
   :subname "target/db/sample"
   :user "sa"
   :password ""})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
