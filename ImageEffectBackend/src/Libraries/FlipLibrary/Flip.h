#ifndef FLIP_H
#define FLIP_H
#include "../Pixel.h"
#include <vector>
#include <algorithm>
using namespace std;

void HorizontalFlip(vector<vector<Pixel>> &image);
void VerticalFlip(vector<vector<Pixel>> &image);

#endif