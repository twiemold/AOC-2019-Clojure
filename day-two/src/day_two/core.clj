(ns day-two.core
  (:require [clojure.string :as str]))

(defn opcode-func [code]
  (case code
    1 +
    2 *))

(defn intcode-processor [program]
  (loop [prog program
         curr 0]
    (let [opcode (nth prog curr)
          op-loc-one (nth prog (+ curr 1))
          op-loc-two (nth prog (+ curr 2))
          loc (nth prog (+ curr 3))] 
     (if (= opcode 99)
        prog
        (recur (assoc prog loc ((opcode-func opcode) 
                                (nth prog op-loc-one) 
                                (nth prog op-loc-two))) 
               (+ curr 4))))))

(defn get-noun-verb-for [value program]
  (loop [prog program
         target value 
         interval [[0 0] [99 99]]
         coords (for [x (range (dec (get-in interval [0 0])) (get-in interval [1 0])) 
                       y (range (dec (get-in interval [0 1])) (get-in interval [1 1]))] 
                  (seq [(inc x) (inc y)]))
         noun (first (first coords))
         verb (second (first coords))]
    (let [attempt (nth (intcode-processor (assoc prog 1 noun 2 verb)) 0)]
      (if (= attempt target)
        (vector noun verb)
        (recur prog 
               target 
               interval
               (rest coords)
               (first (first coords))
               (second (first coords)))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [grav-prog (vec (map parse-long (str/split (slurp "input.txt") #",")) )
        ;; part two
        noun-verb (get-noun-verb-for 19690720 grav-prog)]
    ;; part one
    ;;(println "Position 0 is" (nth (intcode-processor (assoc grav-prog 1 12 2 2)) 0)) 
    (println "Answer is:" (+ (* 100 (first noun-verb)) (second noun-verb)))
    ))

(comment 
  (-main) 
  (nth (intcode-processor (assoc prog 1 64 2 21)) 0)

  (intcode-processor (vec '(1,9,10,3,2,3,11,0,99,30,40,50)))
  )
