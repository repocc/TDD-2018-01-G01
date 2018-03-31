(ns data-processor (:require [state-initializer :refer :all]))

(defn initialize-processor [rules]
  (get-state rules))
         
(defn process-data [state new-data]
  [nil []])
         
(defn query-counter [state counter-name counter-args]
  0)
