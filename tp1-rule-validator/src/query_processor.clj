(ns query-processor)

(defn get-counter [name state]
  (filter #(= (% :name) name)  (:rules state))
  )

(defn equal-args? [keyTT args]
  (= keyTT args))

(defn maybe-get-value [row params]
  (:value (first (filter #(equal-args? (:key %) params) (:truth-table (first row))))))

(defn get-value [row params]
  (if (nil? (maybe-get-value row params)) 0 (maybe-get-value row params)))

(defn get-query-counter-value [state counter-name counter-args]
  (get-value (get-counter counter-name state) counter-args)
  )
