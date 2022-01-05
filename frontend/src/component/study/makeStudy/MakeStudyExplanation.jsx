import React, { PureComponent } from "react";
import style from "../../../css/study/study_make/study_make.module.css";

class MakeStudyExplanation extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      explanation: "",
    };
    this.handleExplanation = this.handleExplanation.bind(this);
  }
  handleExplanation = (e) => {
    this.setState({
      explanation: e.target.value,
    });
    this.props.handleExplanation(this.state.explanation);
  }
  render() {
    return (
      <React.Fragment>
        <div className={style["main__slide-explanation"]}>
          부가설명
          <br />
          <textarea
            className={style["main__slide-explanation-textarea"]}
            name="explanation"
            required
            wrap="hard"
            value={this.state.explanation}
            onChange={this.handleExplanation}
          ></textarea>
        </div>
      </React.Fragment>
    );
  }
}

export default MakeStudyExplanation;
