## Pre-processing for raw images generated from app

# Rename:
1. Batch rename with rename.py
    ```rename.py <extension> <old-label> <new-label>```
    # rename.py takes three arguments:
    1. file extension of image generated (.jpg in this case)
    2. Part of old label to be replaced
    3. New label to replace with.

        - rename.py .jpg ka alphabet_ka

# Reshape:
1. Reshape raw images to manageable size (28*28)
    
