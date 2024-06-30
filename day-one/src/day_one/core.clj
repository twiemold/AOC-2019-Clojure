(ns day-one.core
  (:require [clojure.math :as math]))

(defn calc-fuel [module] 
  (-> (/ module 3) 
      (math/floor) 
      (- 2)
      (int)
      )
  )

(defn recur-fuel [module]
  (loop [modl module
         prior []]
    (let [fuel (calc-fuel modl)] 
      (if (neg? fuel) 
        (reduce + prior) 
        (recur fuel (conj prior fuel)))))
  )

(def memo-recur-fuel (memoize recur-fuel))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [modules (map parse-long 
                     (line-seq (clojure.java.io/reader "input.txt")))]
    ;; part one
    ;; (print "The sum is: " (reduce + (map calc-fuel modules))) 
    (print "The sum is: " 
           (reduce + (map memo-recur-fuel modules))) )
  )

(comment
  (-main)
  (calc-fuel 14)
  (map recur-fuel '(14))
  (map recur-fuel '(1969))
  (map calc-fuel '(12 1969))
  (reduce + (map calc-fuel '(12 1969)))
  )
