;;;; Solution for 113. Making Data Dance

;Write a function that takes a variable number of integer arguments. If the output is coerced into a string, it should return a comma (and space) separated list of the inputs sorted smallest to largest. If the output is coerced into a sequence, it should return a seq of unique input elements in the same order as they were entered.

; special restriction: proxy

(deftype MyProxy [inner_seq]
    clojure.lang.IPersistentList
    (seq [this] (if (empty? inner_seq) nil (distinct inner_seq)))
    Object
    (toString [this] (clojure.string/join ", " (sort inner_seq)))
)


(defn my-proxy
  [& args]
    (MyProxy. args)
)

(def __ my-proxy)

;tests

(= "1, 2, 3" (str (__ 2 1 3)))
(= '(2 1 3) (seq (__ 2 1 3)))
(= '(2 1 3) (seq (__ 2 1 3 3 1 2)))
(= '(1) (seq (apply __ (repeat 5 1))))
(= "1, 1, 1, 1, 1" (str (apply __ (repeat 5 1))))
(and (= nil (seq (__)))
     (=  "" (str (__))))
