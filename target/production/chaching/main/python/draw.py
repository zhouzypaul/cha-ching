import matplotlib.pyplot as plt

print("Hello world")

plt.figure()
plt.ion()

plt.plot([1, 2, 3, 4], [5, 6, 7, 8])
plt.show()
plt.ginput(timeout=3000)
