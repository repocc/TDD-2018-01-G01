(ns signals-processor
  (:require [condition-processor :refer :all]
                       [query-processor :refer :all]))

(def process-signal [ signal data past-data ]
  (if (pass-condition? (get signal :condition) data past-data)
    (apply-operation (get signal :operation) data past-data)))

