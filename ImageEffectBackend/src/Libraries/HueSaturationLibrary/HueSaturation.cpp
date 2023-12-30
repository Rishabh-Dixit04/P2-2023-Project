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

            float saturation = 0.0f;
            float hue = 0.0f;

            if (delta > 0.0f) {
                saturation = (lightness < 0.5f) ? (delta / (maxVal + minVal)) : (delta / (2.0f - maxVal - minVal));

                if (maxVal == r) {
                    hue = (g - b) / delta + ((g < b) ? 6.0f : 0.0f);
                } else if (maxVal == g) {
                    hue = (b - r) / delta + 2.0f;
                } else {
                    hue = (r - g) / delta + 4.0f;
                }

                hue /= 6.0f;
            }

            // Adjust saturation and hue
            saturation += saturationValue / 100.0f;
            hue += hueValue / 100.0f;

            // Ensure saturation and hue are within valid ranges
            saturation = std::max(0.0f, std::min(1.0f, saturation));
            hue = std::max(0.0f, std::min(1.0f, hue));

            // Convert HSL back to RGB
            float q = (lightness < 0.5f) ? (lightness * (1.0f + saturation)) : (lightness + saturation - lightness * saturation);
            float p = 2.0f * lightness - q;

            pixel.r = static_cast<int>(hueToRGB(p, q, hue + 1.0f / 3.0f) * 255);
            pixel.g = static_cast<int>(hueToRGB(p, q, hue) * 255);
            pixel.b = static_cast<int>(hueToRGB(p, q, hue - 1.0f / 3.0f) * 255);

            // Clamp to 8-bit per channel
            pixel.r = std::max(0, std::min(255, pixel.r));
            pixel.g = std::max(0, std::min(255, pixel.g));
            pixel.b = std::max(0, std::min(255, pixel.b));
        }
    }
}






