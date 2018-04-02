(ns data-processor (:use [state-initializer :refer :all]))

(defn initialize-processor [rules]
  (get-state rules))
         
(defn process-data [state new-data]
  [(process-new-state state new-data)  (process-signals state data past-data)])
         
(defn query-counter [state counter-name counter-args]
  0)


(defn process-new-state [old-state new-data]
      (nil))

(defn process-signals [state data past-data] ([]))


;;return true if the condition is met
(defn pass-condition? [condition data past-data] (true))

;;should return new counter with its updated state
(defn inc-counter [counter counter-args] (nil))

;;concat counters
(defn join-counters [list1 list2] (join list1 list2))