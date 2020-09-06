import os, sys
from pathlib import Path


# generator function to get files of specified extension
def files(path,extension):
    for file in os.listdir(path):
        if os.path.isfile(os.path.join(path, file)) and extension in file:
            yield file

# maps arguments list to a dictionary
def args_map(arguments):
    options = {
        "extension":arguments[1],
        "old_label":arguments[2],
        "new_label":arguments[3],
    }
    return options

pwd = Path().absolute()
args = args_map(sys.argv)


for file in files(pwd,args['extension']):
    if args['old_label'] in file:
        os.rename(os.path.join(pwd, file), os.path.join(pwd, file.replace(args['old_label'],args['new_label'])))
