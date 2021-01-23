(ns clojure-course.lab-3-1)

(def threads 8)

(defn my-partition
  ([n coll] (let [c (count coll)
                  batch-size (Math/ceil (/ c n))]
              (my-partition batch-size coll n n)))
  ([batch-size coll current-step steps]
   (if (= current-step 0)
     (list)
     (lazy-seq
       (cons (take batch-size coll) (my-partition batch-size (drop batch-size coll) (dec current-step) steps))))))

(defn my-pmap [f batches]
  (->>
    (map #(future (f %)) batches)
    (doall)
    (map deref)
    (doall)))

(defn pfilter
  [pred coll] (->> (partition threads coll)
                   (my-pmap #(filter pred %))
                   (apply concat)))