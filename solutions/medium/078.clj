;;;;; Solution for 78. Reimplement Trampoline


;; Reimplement the function described in "Intro to Trampoline".

;; The trampoline function takes a function f and a variable number of parameters. Trampoline calls f with any parameters that were supplied. If f returns a function, trampoline calls that function with no arguments.
;; This is repeated, until the return value is not a function, and then trampoline returns that non-function value. This is useful for implementing mutually recursive algorithms in a way that won't consume the stack.


;; special restriction:  trampoline

(defn my-trampoline
  ([fun in] (my-trampoline (fun in)))
  ([arg]
    (if (fn? arg)
      (recur (arg))
      arg
    )
  )
)


(def __ my-trampoline)

; tests

(= (letfn [(triple [x] #(sub-two (* 3 x)))
          (sub-two [x] #(stop?(- x 2)))
          (stop? [x] (if (> x 50) x #(triple x)))]
    (__ triple 2))
  82)


(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
          (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
    (map (partial __ my-even?) (range 6)))
  [true false true false true false])
