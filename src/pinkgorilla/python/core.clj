(ns pinkgorilla.python.core
  (:require
   [clojure.datafy :refer [datafy]]
   [libpython-clj.python :as py]
   [pinkgorilla.notebook-app.system]))

(defn py-initialize! []
  (let [config (pinkgorilla.notebook-app.system/get-setting [:python])]
    (println "python config: " config)
    (py/initialize!
     :python-executable (:python-executable config)
     :library-path (:library-path config))))

(defn- convert 
  "extracts useful information from an item of a python namespace
   this exrtraction is useful, as some python items contain clj
   namespace which cannot be serialized with edn.
   "
  [item]
  (if (map? item)
    (select-keys item [:name :doc :type])
    {}))

(defn pydoc
  "shows he documentation of a python namespace
   Make sure you require python-namespace with :bind-ns
     (require-python '[numpy :as numpy :bind-ns])
     (pydoc numpy)
   "
  [py-namespace]
  ^:R [:p/pydoc
       (map convert
            (vals (datafy py-namespace)))])

(comment

  "{:value-response {:type :reagent, :content 
 {:hiccup ^{:R true} 
 [:p/pydoc (
 {:args [\"object\" \"maxwidth\" \"output\" \"toplevel\"], 
 :varkw nil, :name \"info\", :varargs nil, :type :function, 
 :kwonlydefaults nil, 
 :defaults [nil 76 ^{:added \"1.0\", :ns #namespace[clojure.core], 
 :name *out*, 
 :tag java.io.Writer, 
 :doc \"A java.io.Writer object representing standard output for print operations.\\n\\n  Defaults to System/out, wrapped in an OutputStreamWriter\"} #'clojure.core/*out* \"numpy\"], :module \"numpy\", :kwonlyargs [], 
 :str \"<function info at 0x7f8ff2ec3050>\", 
 :annotations {}, 
 :arglists [[& [{object :object, maxwidth :maxwidth, output :output, toplevel :toplevel, :or {object nil, maxwidth 76, output ^{:added \"1.0\", :ns #namespace[clojure.core], :name *out*, :tag java.io.Writer, :doc \"A java.io.Writer object representing standard output for print operations.\\n\\n  Defaults to System/out, wrapped in an OutputStreamWriter\"} #'clojure.core/*out*, toplevel \"numpy\"}}]] [& [{object :object, maxwidth :maxwidth, output :output, :or {object nil, maxwidth 76, output ^{:added \"1.0\", :ns #namespace[clojure.core], :name *out*, :tag java.io.Writer, :doc \"A java.io.Writer object representing standard output for print operations.\\n\\n  Defaults to System/out, wrapped in an OutputStreamWriter\"} #'clojure.core/*out*}}]] [& [{object :object, maxwidth :maxwidth, :or {object nil, maxwidth 76}}]] [& [{object :object, :or {object nil}}]] []], :flags #{:fn? :callable?}, :doc \"\\n    Get help information for a function, class, or module.\\n\\n    Parameters\\n    ----------\\n    object : object or str, optional\\n        Input object or name to get information about. If `object` is a\\n        numpy object, its docstring is given. If it is a string, available\\n        modules are searched for matching objects.  If None, information\\n        about `info` itself is returned.\\n    maxwidth : int, optional\\n        Printing width.\\n    output : file like object, optional\\n        File like object that the output is written to, default is\\n        ``stdout``.  The object has to be opened in 'w' or 'a' mode.\\n    toplevel : str, optional\\n        Start search at this level.\\n\\n    See Also\\n    --------\\n    source, lookfor\\n\\n    Notes\\n    -----\\n    When used interactively with an object, ``np.info(obj)`` is equivalent\\n    to ``help(obj)`` on the Python prompt or ``obj?`` on the IPython\\n    prompt.\\n\\n    Examples\\n    --------\\n    >>> np.info(np.polyval) # doctest: +SKIP\\n       polyval(p, x)\\n         Evaluate the polynomial p at x.\\n         ...\\n\\n    When using a string for `object` it is possible to get multiple results.\\n\\n    >>> np.info('fft') # doctest: +SKIP\\n         *** Found in numpy ***\\n    Core FFT routines\\n    ...\\n         *** Found in numpy.fft ***\\n     fft(a, n=None, axis=-1)\\n    ...\\n         *** Repeat reference found in numpy.fft.fftpack ***\\n         *** Total of 3 references found. ***\\n\\n    \"})], :map-keywords true, :widget true}}}")