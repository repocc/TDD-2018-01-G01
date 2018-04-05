(ns data-processor (:use [state-initializer :refer :all]))

(defn initialize-processor [rules]
  (get-state rules))

(defn process-data [state new-data]
      (let [new-state (process-new-state state new-data)])
  [(new-state) (process-signals new-state data past-data)])

(defn query-counter [state counter-name counter-args]
  0)


(defn process-new-state [old-state new-data]
      (do (def rules (increment-counters old-state new-data))
          (def past-data (save-past-data old-state new data))
          ({:rules rules :past-data past-data})))

(defn process-signals [state data past-data] ([]))


;;return true if the condition is met
(defn pass-condition? [condition data past-data] (true))

;;should return new counter with its updated state
(defn inc-counter [counter counter-args] (nil))

;;concat counters
(defn join-counters [list1 list2] (join list1 list2))