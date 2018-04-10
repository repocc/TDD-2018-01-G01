(ns signals-processor
  (:require [condition-processor :refer :all]
                       [query-processor :refer :all]))

(def process-signal [ signal state data past-data ]
  (if (pass-condition? (get signal :condition) data past-data)
    (eval-condition state data past-data)))

