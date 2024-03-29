(ns data-processor (:use [state-initializer :refer :all]
                         [query-processor :refer :all]
                         [signals-processor :refer :all]
                         [counter-increaser :refer :all]
                         [data-saver :refer :all]))

(defn should-save-data? [state new-data]
  (any? true? (map #(useful-data-for-rule? new-data (:condition %)) (:rules state))))

(defn transform-state [old-state with-past-data]
  {:rules (:rules old-state)
   :past-data with-past-data})

(defn process-state [state new-data]
  (if(should-save-data? state new-data)
    (transform-state (process-counter state new-data) (conj (get state :past-data) new-data))
    (transform-state (process-counter state new-data) (:past-data state))))

(defn initialize-processor [rules]
  (get-state rules))


(defn process-data [state new-data]
  [(process-state state new-data) (process-signals state new-data)])

(defn query-counter [state counter-name counter-args]
  (get-query-counter-value state counter-name counter-args))

(defn add-rule [state new-rules]
  {:rules (concat (:rules state) (:rules (initialize-processor new-rules)))
   :past-data (:past-data state)})

