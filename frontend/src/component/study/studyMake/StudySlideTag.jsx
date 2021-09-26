import React, { PureComponent } from "react";

class StudySlideTag extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      input: "",
      tags: ["#web", "#스터디"],
    };
    this.removeList = this.removeList.bind(this);
    this.handleInput = this.handleInput.bind(this);
    this.handleCreate = this.handleCreate.bind(this);
  }
  handleInput = (event) => {
    this.setState({
      input: "#" + event.target.value,
    });
  };
  handleCreate = (event) => {
    this.setState({
      tags: this.state.tags.concat(this.state.input),
      input: " ",
    });
  };
  removeList = (i) => {
    this.setState({
      tags: [
        ...this.state.tags.slice(0, i),
        ...this.state.tags.slice(i + 1, this.state.tags.length),
      ],
    });
  };
  render() {
    const tagList = this.state.tags.map((tag, i) => (
      <li
        className="main__slide-tag-list-item"
        key={i}
        onClick={() => this.removeList(i)}
      >
        {tag}
      </li>
    ));
    return (
      <React.Fragment>
        <div className="main__slide-tag">
          태그 <br />
          <form action="#" method="POST" name="study_tag">
            <input
              onChange={this.handleInput}
              className="studymake_input"
              name="tag"
              autoComplete="off"
              placeholder="태그 입력 (클릭 시 삭제)"
            />
            <button
              onClick={this.handleCreate}
              className="main__slide-tag-button"
              type="button"
            >
              <p>+</p>
            </button>
            <ul className="main__slide-tag-list">{tagList}</ul>
          </form>
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlideTag;
