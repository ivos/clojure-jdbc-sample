(defproject clojure-jdbc-sample "0.1.0-SNAPSHOT"
  :dependencies [
    [org.clojure/clojure "1.7.0"]
    [org.clojure/java.jdbc "0.6.0-alpha1"]
    [com.h2database/h2 "1.3.176"]
    [ragtime "0.5.2"]
  ]
  :main ^:skip-aot clojure-jdbc-sample.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
