#include "HueSaturation.h"
#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

float hueToRGB(float p, float q, float t) {
    if (t < 0.0f) t += 1.0f;
    if (t > 1.0f) t -= 1.0f;
    if (t < 1.0f / 6.0f) return p + (q - p) * 6.0f * t;
    if (t < 1.0f / 2.0f) return q;
    if (t < 2.0f / 3.0f) return p + (q - p) * (2.0f / 3.0f - t) * 6.0f;
    return p;
}

void applyHueSaturation(std::vector<std::vector<Pixel>> &imageVector, float saturationValue, float hueValue) {
    for (auto &row : imageVector) {
        for (auto &pixel : row) {
            // Convert RGB to HSL
            float r = pixel.r / 255.0f;
            float g = pixel.g / 255.0f;
            float b = pixel.b / 255.0f;

            float maxVal = std::max({r, g, b});
            float minVal = std::min({r, g, b});

            float lightness = (maxVal + minVal) / 2.0f;
            float delta = maxVal - minVal;

            float hue, saturation;

            if (delta == 0) {
                hue = 0.0f;
                saturation = 0.0f;
            } else {
                saturation = delta / (1 - std::abs(2 * lightness - 1));

                if (maxVal == r) {
                    hue = (g - b) / delta + (g < b ? 6 : 0);
                } else if (maxVal == g) {
                    hue = (b - r) / delta + 2;
                } else {
                    hue = (r - g) / delta + 4;
                }

                hue /= 6.0f;
            }

            // Adjust saturation and hue
            saturation += saturationValue / 100.0f;
            hue += hueValue / 100.0f;

            // Ensure saturation and hue are within valid ranges
            saturation = std::max(0.0f, std::min(1.0f, saturation));
            hue = std::fmod(hue, 1.0f);

            // Convert HSL back to RGB
            float C = (1 - std::abs(2 * lightness - 1)) * saturation;
            float X = C * (1 - std::abs(std::fmod(hue * 6, 2) - 1));
            float m = lightness - C / 2;

            float rNew, gNew, bNew;

            if (hue < 1.0 / 6) {
                rNew = C;
                gNew = X;
                bNew = 0;
            } else if (hue < 2.0 / 6) {
                rNew = X;
                gNew = C;
                bNew = 0;
            } else if (hue < 3.0 / 6) {
                rNew = 0;
                gNew = C;
                bNew = X;
            } else if (hue < 4.0 / 6) {
                rNew = 0;
                gNew = X;
                bNew = C;
            } else if (hue < 5.0 / 6) {
                rNew = X;
                gNew = 0;
                bNew = C;
            } else {
                rNew = C;
                gNew = 0;
                bNew = X;
            }

            pixel.r = static_cast<int>((rNew + m) * 255);
            pixel.g = static_cast<int>((gNew + m) * 255);
            pixel.b = static_cast<int>((bNew + m) * 255);
        }
    }
}





