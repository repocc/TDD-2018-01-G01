(ns signals-processor
  (:require [condition-processor :refer :all]
                       [query-processor :refer :all]))


(def process-signal [ signal data past-data ]
  (if (pass-condition? (get signal :condition) data past-data)
    ()))

(defn counter-value? [value] (= (nth value 0) 'counter-value ))

(defn eval-signal [operation data past-data]
  "Given a current data and a past data, determines if the given condition is true"
  (cond
    (condition-processor/current? operation) (get data (second operation))
    (condition-processor/past? operation) (get past-data (second operation))
    (counter-value? operation) (query-processor/get-value data (nth operation 2))
    :else
    (apply-operation (first operation) (map #(eval-signal % data past-data) (rest operation)))))