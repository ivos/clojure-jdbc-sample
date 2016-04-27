(defproject clojure-jdbc-sample "0.1.0-SNAPSHOT"
  :dependencies [
    [org.clojure/clojure "1.7.0"]
    [org.clojure/java.jdbc "0.6.0-alpha2"]
    [com.h2database/h2 "1.3.176"]
    [org.flywaydb/flyway-core "3.2.1"]
    [com.layerware/hugsql "0.4.7"]
    [org.clojure/tools.logging "0.3.1"]
    [ch.qos.logback/logback-classic "1.1.7"]
  ]
  :main ^:skip-aot clojure-jdbc-sample.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {
    "db-migrate" ["run" "-m" "clojure-jdbc-sample.migration/migrate"]
    "db-clean" ["run" "-m" "clojure-jdbc-sample.migration/clean"]
  })
