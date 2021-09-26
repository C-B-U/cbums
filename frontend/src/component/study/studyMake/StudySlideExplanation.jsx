import React, { PureComponent } from "react";

class StudySlideExplanation extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="main__slide-explanation">
          부가설명
          <br />
          <textarea
            name="explanation"
            style={{ resize: "none", width: "90%", height: "130px" }}
            required
            wrap="hard"
          ></textarea>
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlideExplanation;
