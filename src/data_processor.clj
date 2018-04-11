(ns data-processor (:use [state-initializer :refer :all]
                         [query-processor :refer :all]
                         [signals-processor :refer :all]
                         [counter-increaser :refer :all]))

(defn should-save-data? [state new-data] true)

(defn transform-state [old-state with-past-data]
  {:rules (:rules old-state)
   :past-data with-past-data})

(defn process-state [state new-data]
  (if(should-save-data? state new-data)
    (transform-state (process-counter state new-data) (conj (get state :past-data) new-data))
    state))

(defn initialize-processor [rules]
  (get-state rules))

(defn process-data [state new-data]
      (do (print "NEW STATE: ----- \n" state"\n\n" ))
  [(process-state state new-data) [(process-signals state new-data)]])

(defn query-counter [state counter-name counter-args]
  (get-query-counter-value state counter-name counter-args))

