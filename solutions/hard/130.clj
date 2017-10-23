;;; solution for 130. Tree reparenting

;Every node of a tree is connected to each of its children as well as its parent. One can imagine grabbing one node of a tree and dragging it up to the root position, leaving all connections intact. For example, below on the left is a binary tree. By pulling the "c" node up to the root, we obtain the tree on the right.

;Note it is no longer binary as "c" had three connections total -- two children and one parent. Each node is represented as a vector, which always has at least one element giving the name of the node as a symbol. Subsequent items in the vector represent the children of the node. Because the children are ordered it's important that the tree you return keeps the children of each node in order and that the old parent node, if any, is appended on the right. Your function will be given two args -- the name of the node that should become the new root, and the tree to transform.


(defn reparent
    ([search-key tree] (reparent search-key tree nil))
    ([search-key tree reparented]
    (if (nil? tree)
         nil
         (let [el (first tree)]
            (if (= search-key el)
                (if (nil? reparented) tree;key is root
                    (concat tree [reparented])
                )
                ;in this case just try all children
                (let [results (for [i (range 1 (count tree))
                                    :let [new-reparented (concat (take i tree) (drop (inc i) tree) (if (nil? reparented) nil  [reparented]))
                                          res (reparent search-key (nth tree i) new-reparented)]
                                    :when (not (nil? res))]
                                    res)]
                    (first results)
                )
            )
        )
    ))
)


(def __ reparent)


;;tests

(= '(n)
   (__ 'n '(n)))


(= '(a (t (e)))
   (__ 'a '(t (e) (a))))


(= '(e (t (a)))
   (__ 'e '(a (t (e)))))


(= '(a (b (c)))
   (__ 'a '(c (b (a)))))

(= '(d
      (b
        (c)
        (e)
        (a
          (f
            (g)
            (h)))))
  (__ 'd '(a
            (b
              (c)
              (d)
              (e))
            (f
              (g)
              (h)))))


(= '(c
      (d)
      (e)
      (b
        (f
          (g)
          (h))
        (a
          (i
          (j
            (k)
            (l))
          (m
            (n)
            (o))))))
   (__ 'c '(a
             (b
               (c
                 (d)
                 (e))
               (f
                 (g)
                 (h)))
             (i
               (j
                 (k)
                 (l))
               (m
                 (n)
                 (o))))))
