(ns signals-processor
  (:require [condition-processor :refer :all]))

(defn get-operation-result [operation data past-data state]
  (condition-processor/eval-condition operation data past-data state))

(defn process-signal [signal data past-data state]
  (if (pass-condition? (get signal :condition) data past-data)
    {(:name signal) (get-operation-result (:operation signal) data (get-selected-past-data (get signal :condition) data past-data) state)}))

(def is-signal? (fn [rule] (=(:type rule) "signal")))

(defn try-process-signal[signal new-data state]
  (try(process-signal signal new-data (:past-data state) state)
    (catch Exception e)))

(defn process-signals [state new-data]
  (remove nil? (conj (map #(try-process-signal % new-data state) (filter is-signal? (:rules state))))))


