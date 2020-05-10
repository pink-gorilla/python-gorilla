# python-gorilla

UI renderers for libPythonClj + Panthera

## Renderer Implementations
- python maplotlib plots for libpython-clj

## Demo Notebooks
- techml dataset 
- libPythonClj
- Panthera

# Usage (notebook as library)

Pyrhon cnfiguratun (see config.edn)
```
:python {:python-executable "~/.conda/envs/pyclj/bin/python3.7"
           :library-path "~/.conda/envs/pyclj/lib/libpython3.7m.dylib"}
``` 

## run notebook

```
lein pinkgorilla
```
This will run pinkgorilla notebook as a library, with demo notebooks loaded into the explorer.

## in repl

Alteratively start a repl with profile "notebook" and notebook will load also.





