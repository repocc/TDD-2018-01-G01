(ns query-processor)

(defn get-counter [name state]
  (filter #(= (% :name) name)  (:rules state))
  )

(defn equal-args? [keyTT args]
  (= (compare keyTT args) 0)
  )

(defn get-value [row params]
  (:value (first (filter #(equal-args? (% :key) params) (:truth-table (first row))))))
