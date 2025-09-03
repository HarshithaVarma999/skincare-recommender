import React, { useState } from "react";
import api from "./api"; // axios instance
import styles from "./App.module.css";
import { motion } from "framer-motion";

export default function App() {
  const [step, setStep] = useState(1);
  const [skinType, setSkinType] = useState("");
  const [concerns, setConcerns] = useState([]);
  const [topRecommendations, setTopRecommendations] = useState([]);
  const [communityRecommendations, setCommunityRecommendations] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const [userRecForm, setUserRecForm] = useState({
    productType: "",
    brandName: "",
    productName: "",
  });
  const [submissionStatus, setSubmissionStatus] = useState(null);

  // 👉 Handlers
  const handleStart = () => setStep(2);

  const handleSkinTypeChange = (e) => setSkinType(e.target.value);

  const handleConcernChange = (e) => {
    const value = e.target.value;
    setConcerns((prev) =>
      prev.includes(value) ? prev.filter((c) => c !== value) : [...prev, value]
    );
  };

  const handleRestart = () => {
    setStep(1);
    setSkinType("");
    setConcerns([]);
    setTopRecommendations([]);
    setCommunityRecommendations([]);
    setError(null);
    setUserRecForm({ productType: "", brandName: "", productName: "" });
    setSubmissionStatus(null);
  };

  const handleSubmit = async () => {
    if (!skinType || concerns.length === 0) return;

    setLoading(true);
    setError(null);

    const queryParams = new URLSearchParams();
    queryParams.append("skinType", skinType);
    concerns.forEach((c) => queryParams.append("concerns", c));

    try {
      const response = await api.get(
        `/api/skincare/recommendations?${queryParams.toString()}`
      );

      setTopRecommendations(response.data.topRecommendations || []);
      setCommunityRecommendations(response.data.communityRecommendations || []);
      setStep(3);
    } catch (e) {
      console.error("Error fetching recommendations:", e);
      setError("⚠️ Failed to get recommendations. Please check the backend server.");
    } finally {
      setLoading(false);
    }
  };

  const handleUserRecFormChange = (e) => {
    const { name, value } = e.target;
    setUserRecForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleUserRecSubmit = async (e) => {
    e.preventDefault();
    setSubmissionStatus(null);
    try {
      const payload = {
        skinType: skinType,
        concerns: concerns.join(","), // CSV in DB
        productType: userRecForm.productType,
        brandName: userRecForm.brandName,
        productName: userRecForm.productName,
      };

      const response = await api.post(
        "/api/user-recommendations/submit",
        payload
      );
      if (response.status === 200) {
        setSubmissionStatus("success");
        setUserRecForm({ productType: "", brandName: "", productName: "" });
      }
    } catch (e) {
      console.error("Error submitting recommendation:", e);
      setSubmissionStatus("error");
    }
  };

  // 👉 Render content
  const renderContent = () => {
    if (error) {
      return (
        <div className={styles.errorContainer}>
          <p>{error}</p>
          <button onClick={handleRestart} className={styles.buttonPrimaryBlue}>
            🔄 Try Again
          </button>
        </div>
      );
    }

    switch (step) {
      case 1:
        return (
          <div className={styles.welcomeScreen}>
            <h2 className={styles.title}>🌿 Find Your Perfect Skincare Routine</h2>
            <p className={styles.subtitle}>
              Answer a few quick questions to get personalized product
              recommendations. 💧
            </p>
            <button onClick={handleStart} className={styles.buttonPrimaryBlue}>
               Get Started
            </button>
          </div>
        );

      case 2:
        return (
          <div>
            <h2 className={styles.title}>💧 Your Skin Profile</h2>

            {/* Skin Type */}
            <div className={styles.questionSection}>
              <h3 className={styles.questionHeading}>1. What is your skin type?</h3>
              <div className={styles.optionGrid}>
                {["Dry", "Oily", "Combination", "Normal", "Sensitive"].map(
                  (type) => (
                    <label
                      key={type}
                      className={`${styles.optionLabel} ${
                        skinType === type ? styles.optionLabelSelected : ""
                      }`}
                    >
                      <input
                        type="radio"
                        name="skinType"
                        value={type}
                        checked={skinType === type}
                        onChange={handleSkinTypeChange}
                        className={styles.radioInput}
                      />
                      <span>{type}</span>
                    </label>
                  )
                )}
              </div>
            </div>

            {/* Concerns */}
            <div className={styles.questionSection}>
              <h3 className={styles.questionHeading}>
                2. What are your main skin concerns?
              </h3>
              <p className={styles.questionSubtitle}>(Select all that apply)</p>
              <div className={styles.optionGrid}>
                {["Acne", "Fine lines", "Redness", "Hyperpigmentation"].map(
                  (concern) => (
                    <label
                      key={concern}
                      className={`${styles.optionLabel} ${
                        concerns.includes(concern)
                          ? styles.optionLabelSelected
                          : ""
                      }`}
                    >
                      <input
                        type="checkbox"
                        value={concern}
                        checked={concerns.includes(concern)}
                        onChange={handleConcernChange}
                        className={styles.checkboxInput}
                      />
                      <span>{concern}</span>
                    </label>
                  )
                )}
              </div>
            </div>

            {/* Submit */}
            <div className={styles.submitButtonContainer}>
              <button
                onClick={handleSubmit}
                disabled={!skinType || concerns.length === 0}
                className={styles.buttonPrimaryBlue}
              >
                🌿 Get Recommendations
              </button>
            </div>
          </div>
        );

      case 3:
        return (
          <div className={styles.recommendationsBox}>
            <h2 className={styles.recommendationsTitle}>
              🌿 Your Personalized Routine
            </h2>
            <p className={styles.subtitle}>
              Based on your skin type and concerns, here are your top picks:
            </p>

            {/* Top Recommendations */}
            {topRecommendations.length > 0 ? (
              <ul className={styles.recommendationList}>
                {topRecommendations.map((rec, index) => (
                  <li key={index} className={styles.recommendationItem}>
                    ✔️ {rec}
                  </li>
                ))}
              </ul>
            ) : (
              <p className={styles.noRecsMessage}>No top recommendations available.</p>
            )}

            {/* Community Recommendations */}
            <div className={styles.userRecSection}>
              <h3 className={styles.userRecTitle}>🤝 Community Recommendations</h3>
              {communityRecommendations.length > 0 ? (
                <ul className={styles.recommendationList}>
                  {communityRecommendations.map((rec, index) => (
                    <li key={index} className={styles.recommendationItem}>
                      ✔️ {rec}
                    </li>
                  ))}
                </ul>
              ) : (
                <p className={styles.noRecsMessage}>
                  No community recommendations yet. Be the first to share one!
                </p>
              )}
            </div>

            {/* User submission form */}
            <div className={styles.userRecSection}>
              <h3 className={styles.userRecTitle}>
                ✨ Found a great product? Share your recommendations!
              </h3>
              <form
                onSubmit={handleUserRecSubmit}
                className={styles.userRecForm}
              >
                <input
                  type="text"
                  name="productType"
                  placeholder="e.g., Cleanser"
                  value={userRecForm.productType}
                  onChange={handleUserRecFormChange}
                  className={styles.formInput}
                  required
                />
                <input
                  type="text"
                  name="brandName"
                  placeholder="e.g., CeraVe"
                  value={userRecForm.brandName}
                  onChange={handleUserRecFormChange}
                  className={styles.formInput}
                  required
                />
                <input
                  type="text"
                  name="productName"
                  placeholder="e.g., Foaming Facial Cleanser"
                  value={userRecForm.productName}
                  onChange={handleUserRecFormChange}
                  className={styles.formInput}
                  required
                />

                {/* Buttons aligned side by side */}
                <div className={styles.actionButtons}>
                  <button type="submit" className={styles.buttonPrimaryBlue}>
                    ✍️ Submit Recommendation
                  </button>
                  <button
                    type="button"
                    onClick={handleRestart}
                    className={styles.buttonPrimaryBlue}
                  >
                    🔄 Start Over
                  </button>
                </div>


                {submissionStatus === "success" && (
                  <p className={styles.successMessage}>
                    🎉 Thanks for the suggestion!
                  </p>
                )}
                {submissionStatus === "error" && (
                  <p className={styles.errorMessage}>
                    ❌ Error submitting. Please try again.
                  </p>
                )}
              </form>
            </div>
          </div>
        );

      default:
        return null;
    }
  };

  return (
    <div className={styles.appContainer}>
      <div className={step === 2 ? styles.quizScreen : styles.card}>
        {renderContent()}
      </div>
    </div>
  );
}
