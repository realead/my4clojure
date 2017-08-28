;;;; Solution for 61. Map Constuction

;;; Write a function which takes a vector of keys and a vector of values and constructs a map from them.

;; special restriction: zipmap



(defn my-zipmap
  ([keys values] (my-zipmap keys values {}))
  ([keys values res]
     (if (or (empty? keys) (empty? values))
           res
           (recur (rest keys) (rest values) (assoc res (first keys) (first values)))
     )
  )
)

; tests
(= (my-zipmap [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
(= (my-zipmap [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
(= (my-zipmap [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})


