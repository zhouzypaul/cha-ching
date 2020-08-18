import argparse
import matplotlib.pyplot as plt


def run():
    print("Hello world")

    plt.figure()
    plt.ion()

    plt.plot([1, 2, 3, 4], [5, 6, 7, 8])
    plt.show()
    plt.ginput(timeout=3000)


def parse_arguments():
    # Create argument parser
    parser = argparse.ArgumentParser()

    # Positional mandatory arguments
    parser.add_argument("dict", help="dictionary", type=dict)

    # Optional arguments
    # parser.add_argument("-g", "--giou", help="use generalized iou", action='store_true', default=False)

    # Parse arguments
    args = parser.parse_args()

    return args


if __name__ == '__main__':
    # args = parse_arguments()
    run()
