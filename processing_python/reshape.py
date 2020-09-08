import cv2
import matplotlib.pyplot as plt
image = cv2.imread('./train/ka60.jpg')
# image = cv2.bitwise_not(image)
resized_digit = cv2.resize(image.copy(), (28,28))
grey = cv2.cvtColor(resized_digit.copy(), cv2.COLOR_BGR2GRAY)
ret, thresh = cv2.threshold(grey.copy(), 70, 255, cv2.THRESH_BINARY_INV)
# padded_digit = np.pad(resized_digit, ((5,5),(5,5)), "constant", constant_values=0)


plt.imshow(thresh, cmap="gray")
plt.show()
