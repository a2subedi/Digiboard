import cv2
import numpy as np
import matplotlib.pyplot as plt
image = cv2.imread('./test/ka.jpg')
# image = cv2.bitwise_not(image) 

# edges data lost during resizing. need to test more bluring and resizing

# use Gaussian filtering
image_blurred = cv2.blur(image,(3,3))

plt.subplot(121),plt.imshow(image),plt.title('Original')
plt.xticks([]), plt.yticks([])
plt.subplot(122),plt.imshow(image_blurred),plt.title('Blurred')
plt.xticks([]), plt.yticks([])
plt.show()

resized_digit = cv2.resize(image.copy(), (28,28))
resized_digit_blur = cv2.blur(resized_digit,(3,3))
grey = cv2.cvtColor(resized_digit_blur.copy(), cv2.COLOR_BGR2GRAY)
ret, thresh = cv2.threshold(grey.copy(), 128, 255, cv2.THRESH_BINARY_INV)

rd2 = cv2.resize(image_blurred.copy(), (28,28))
g2 = cv2.cvtColor(rd2.copy(), cv2.COLOR_BGR2GRAY)
ret2, thresh2 = cv2.threshold(g2.copy(), 100 , 255, cv2.THRESH_BINARY_INV)


# padded_digit = np.pad(resized_digit, ((5,5),(5,5)), "constant", constant_values=0)

plt.subplot(121),plt.imshow(thresh),plt.title('Original')
plt.xticks([]), plt.yticks([])
plt.subplot(122),plt.imshow(thresh2),plt.title('Blurred')
plt.xticks([]), plt.yticks([])
plt.show()

# plt.imshow(thresh, cmap="gray")
# plt.show()
