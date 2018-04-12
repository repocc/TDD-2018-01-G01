(ns signals-processor
  (:require [condition-processor :refer :all]))

(defn get-operation-result [operation data past-data state]
  (condition-processor/eval-condition operation data past-data state))

(defn process-signal [signal data past-data state]
  (if (pass-condition? (get signal :condition) data past-data)
    {(:name signal) (get-operation-result (:operation signal) data past-data state)}))

(def is-signal? (fn [rule] (=(:type rule) "signal")))

(defn merge-if-not-nil [& maps]
    (merge (filter #(not (nil? (vals %))) maps)))

(defn process-signals [state new-data]
  (conj (map #(process-signal % new-data (:past-data state) state) (filter is-signal? (:rules state)))))


