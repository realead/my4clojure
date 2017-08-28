;;;; Solution for 107. Simple closures


;;Given a positive integer n, return a function (f x) which computes xn. Observe that the effect of this is to preserve the value of n for use outside the scope in which it is defined


(defn my-pow-fun
     [n]
     (fn worker
         ([x] (worker x n 1))
         ([x k res]
          (if (= 0 k)
               res
               (recur x (dec k) (* res x))
          )
         )
    )
)



;;tests:

(= 256 ((my-pow-fun 2) 16),

       ((my-pow-fun 8) 2))


(= [1 8 27 64] (map (my-pow-fun 3) [1 2 3 4]))

(= [1 2 4 8 16] (map #((my-pow-fun %) 2) [0 1 2 3 4]))
