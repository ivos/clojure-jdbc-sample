(ns clojure-jdbc-sample.customer-repo
  (:require [clojure.java.jdbc :as db]))

(def generated-key (keyword "scope_identity()"))

(defn set-values [values]
  (let [version (:version values)
        no-id (dissoc values :id :version)]
    (assoc no-id :version (inc version))))

(defn where-values [values]
  ["id = ? and version = ?" (:id values) (:version values)])

(defn create-customer [db-spec values]
  (let [defaulted-values (assoc values :version 1)
        [result] (db/insert! db-spec :customer defaulted-values)]
    (assoc defaulted-values :id (generated-key result))))

(defn update-customer [db-spec values]
  (let [values-to-set (set-values values)
        [result] (db/update! db-spec :customer values-to-set (where-values values))]
    (if-not (== 1 result) (throw (RuntimeException. (str "Optimistic lock failed on customer " values))))
    (merge values values-to-set)))

(defn delete-customer [db-spec values]
  (let [[result] (db/delete! db-spec :customer (where-values values))]
    (if-not (== 1 result) (throw (RuntimeException. (str "Optimistic lock failed on customer " values))))
    values))
