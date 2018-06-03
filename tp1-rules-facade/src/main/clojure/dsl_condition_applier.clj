(ns dsl-condition-applier (:use [clojure.string :as string]))

(defn apply-operation [identifier args]
  (cond
    (= (symbol (str identifier)) '=) (apply = args)
    (= (symbol (str identifier)) '!=) (apply not= args)

    (= (symbol (str identifier)) 'and) (every? identity args)
    (= (symbol (str identifier)) 'or) (not (nil?(some identity args)))
    (= (symbol (str identifier)) 'not) (not (every? identity args))

    (= (symbol (str identifier)) '+) (reduce + args)
    (= (symbol (str identifier)) '-) (reduce - args)
    (= (symbol (str identifier)) '*) (reduce * args)
    (= (symbol (str identifier)) '/) (reduce / args)
    (= (symbol (str identifier)) 'mod) (reduce mod args)
    (= (symbol (str identifier)) '*) (reduce - args)

    (= (symbol (str identifier)) '<) (apply < args)
    (= (symbol (str identifier)) '>) (apply > args)
    (= (symbol (str identifier)) '<=) (apply <= args)
    (= (symbol (str identifier)) '>=) (apply >= args)

    (= (symbol (str identifier)) '!=) (apply not= args)
    (= (symbol (str identifier)) 'includes?) (apply string/includes? args)
    (= (symbol (str identifier)) 'starts-with?) (apply string/starts-with? args)
    (= (symbol (str identifier)) 'concat) (apply str args)
    (= (symbol (str identifier)) 'ends-with?) (apply string/ends-with? args)
    (= (str identifier)  "true" ) true
    (= (str identifier) "false" ) false))

