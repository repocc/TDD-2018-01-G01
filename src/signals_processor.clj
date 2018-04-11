(ns signals-processor
  (:require [condition-processor :refer :all]))

(defn is-signal? [rule]
  (=(:type rule) "signal"))

(defn process-signal [ signal data past-data state]
  (if (pass-condition? (get signal :condition) data past-data)
    (condition-processor/eval-condition (get signal :operation) data past-data state)))

(defn process-signals [state new-data]
  (map #(process-signal % new-data (get :past-data state) state) (filter #(is-signal? %) (get state :rules))))


