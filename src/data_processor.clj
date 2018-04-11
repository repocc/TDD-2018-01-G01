(ns data-processor (:use [state-initializer :refer :all]
                         [query-processor :refer :all]
                         [signals-processor :refer :all]
                         [counter-increaser :refer :all]))

(defn initialize-processor [rules]
  (get-state rules))

(defn process-data [state new-data]
  [(process-counter state new-data) [(process-signals state new-data)]])

(defn query-counter [state counter-name counter-args]
  (get-query-counter-value state counter-name counter-args)
  )
