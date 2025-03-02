from flask import Flask, request, jsonify
import joblib
import numpy as np

app = Flask(__name__)

# Load trained model
model = joblib.load("grading_model.pkl")

@app.route('/predict', methods=['POST'])
def predict():
    try:
        # Get JSON data from request
        data = request.get_json()
        print("Received data:", data)


        # Extract features and ensure conversion to standard Python types
        features = np.array([
            float(data["exam_mark"]),
            float(data["lab_mark"]),
            float(data["assignment_mark"]),
            float(data["attendance"])
        ]).reshape(1, -1)

        # Make prediction
        predicted_grade = model.predict(features)[0]

        # Ensure response is JSON serializable
        return jsonify({"predicted_grade": int(predicted_grade)})

    except KeyError as e:
        return jsonify({"error": f"Missing key in request data: {str(e)}"}), 400
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)  # Run Flask API with debug mode
