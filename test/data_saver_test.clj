(ns data-saver-test
  (:require [clojure.test :refer :all]
            [data-saver :refer :all]))

(def data1 {"dasda" "dsaas"})

(def data2 {"hola" 9, "bye" true })

(def data3 {"dasda" "dsaas", "value" 8 , "chau" true, "receiver" false, "subject" 10 })

(def data4 {"value" 8 , "chau" true, "subject" 10 })

(def condition1 '(= (or (current "value") (past "value")) (past "chau")))

(def condition2 '(past "receiver"))

(def condition3 '(or (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))

(def condition '(true))

(deftest test-useful-data1
  (testing "Should return false every time"
    (is (= false
           (useful-data-for-rule? data1 condition1)))
    (is (= false
           (useful-data-for-rule? data1 condition2)))
    )
    (is (= false
         (useful-data-for-rule? data1 condition3)))
    (is (= false
           (useful-data-for-rule? data1 condition)))
  )

(deftest test-useful-data2
  (testing "Should return false every time"
    (is (= false
           (useful-data-for-rule? data2 condition1)))
    (is (= false
           (useful-data-for-rule? data2 condition2)))
    )
  (is (= false
         (useful-data-for-rule? data2 condition3)))
  )


(deftest test-useful-data3
  (testing "Should return true every time"
    (is (= true
           (useful-data-for-rule? data3 condition1)))
    (is (= true
           (useful-data-for-rule? data3 condition2)))
    )
    (is (= true
         (useful-data-for-rule? data3 condition3)))
  )

(deftest test-useful-data4
  (testing "Should return true, false and false"
    (is (= true
           (useful-data-for-rule? data4 condition1)))
    (is (= false
           (useful-data-for-rule? data4 condition2)))
    )
    (is (= false
         (useful-data-for-rule? data4 condition3)))
  )
