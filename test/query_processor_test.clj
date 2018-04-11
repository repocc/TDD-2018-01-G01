(ns query-processor-test
  (:require [clojure.test :refer :all]
            [query-processor :refer :all]
            [data-processor :refer :all]))


(def state {:rules '({:type "counter", :name "email-count", :params [], :condition true,
                          :truth-table ({:key [], :value 8})}
                          {:type "counter", :name "spam-count", :params [], :condition (current "spam"),
                           :truth-table ({:key [], :value 40})} {:type "signal"}
                          {:type "counter", :name "spam-important-table", :params [(current "spam") (current "important")],
                           :condition true, :truth-table ({:key [true true], :value 55}, {:key [true false], :value 66})})
            :past-data []})



(def rules '((define-counter "email-count" []
                             true)
             (define-counter "spam-count" []
                             (current "spam"))
             (define-signal {"spam-fraction" (/ (counter-value "spam-count" [])
                                                (counter-value "email-count" []))}
                            true)
             (define-counter "spam-important-table" [(current "spam")
                                                     (current "important")]
                             true)))


(deftest test-empty-counters
 (testing "Should return the counter row"
          (is (= 0
                 (query-counter (initialize-processor rules) "spam-count" [])))
          )
 )


(deftest get-counter-name
  (testing "Should return the counter row"
    (is (= "email-count"
           (:name (first (get-counter "email-count" state)))))
    (is (= "spam-important-table"
           (:name (first (get-counter "spam-important-table" state)))))

    )
  )


(deftest try-to-get-counter-name
  (testing "Should return a nil value because counter name doesn't exists"
    (is (= true
           (nil? (:name (first (get-counter "email-spam" state))))))
    ))

(deftest get-counter-value
  (testing "Should return the counter value"
    (is (= 40
           (get-value (get-counter "spam-count" state) [])))
    (is (= 8
           (get-value (get-counter "email-count" state) [])))
    (is (= 55
           (get-value (get-counter "spam-important-table" state) [true true])))
    (is (= 66
           (get-value (get-counter "spam-important-table" state) [true false])))
    )
  )

(deftest try-to-get-counter-value
  (testing "Should return a nil value because the counter name doesn't exist or counter-args don't match"
    (is (= true
           (nil? (maybe-get-value (get-counter "not-spam" state) []))))
    (is (= true
           (nil? (maybe-get-value (get-counter "spam-important-table" state) []))))
    (is (= true
           (nil? (maybe-get-value (get-counter "spam-count" state) [true true]))))
    )
  )