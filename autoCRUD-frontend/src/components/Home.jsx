import { useState } from "react";
import "../styles/home.css";
import { createCrud } from "../api/createCrud";

function Home() {
    const [entities, setEntities] = useState([
        { name: "", attributes: [{ name: "", type: "String", nullable: true }] },
    ]);

    const handleEntityNameChange = (index, value) => {
        const updated = [...entities];
        updated[index].name = value;
        setEntities(updated);
    };

    const handleAttributeChange = (entityIndex, attrIndex, field, value) => {
        const updated = [...entities];
        updated[entityIndex].attributes[attrIndex][field] =
            field === "nullable" ? value.target.checked : value;
        setEntities(updated);
    };

    const addAttribute = (entityIndex) => {
        const updated = [...entities];
        updated[entityIndex].attributes.push({
            name: "",
            type: "String",
            nullable: true,
        });
        setEntities(updated);
    };

    const removeAttribute = (entityIndex, attrIndex) => {
        const updated = [...entities];
        updated[entityIndex].attributes.splice(attrIndex, 1);
        setEntities(updated);
    };

    const addEntity = () => {
        setEntities([
            ...entities,
            { name: "", attributes: [{ name: "", type: "String", nullable: true }] },
        ]);
    };

    const removeEntity = (index) => {
        const updated = [...entities];
        updated.splice(index, 1);
        setEntities(updated);
    };

    //   const handleSubmit = () => {
    //     console.log("Submitted Data:", entities);
    //     alert("Check console for submitted structure!");
    //   };
    const handleSubmit = async () => {
        try {
            console.log("Submitted Data:", entities);
            console.log(JSON.stringify(entities));
            const response = await createCrud(JSON.stringify(entities))
            //  await fetch("http://localhost:8080/api/generate-crud", {
            //     method: "POST",
            //     headers: {
            //         "Content-Type": "application/json",
            //     },
            //     body: JSON.stringify(entities),
            

            if (response.ok) {
                const result = await response.json();
                alert("Backend code generation successful!");
                console.log("Backend Response:", result);
            } else {
                alert("Failed to generate backend code.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Error sending data to backend.");
        }
    };


    return (
        <div className="home">
            <div className="title">
                <h1>Auto CRUD</h1>
                <h3>Generates the Backend CRUD operations with just a few clicks</h3>
            </div>

            <div className="form-section">
                {entities.map((entity, entityIndex) => (
                    <div key={entityIndex} className="entity-card">
                        <div className="entity-header">
                            <input
                                type="text"
                                placeholder="Entity Name"
                                value={entity.name}
                                onChange={(e) =>
                                    handleEntityNameChange(entityIndex, e.target.value)
                                }
                                className="input entity-input"
                            />
                            {entities.length > 1 && (
                                <button
                                    onClick={() => removeEntity(entityIndex)}
                                    className="button remove-entity-btn"
                                >
                                    ✖
                                </button>
                            )}
                        </div>

                        <div className="attribute-list">
                            {entity.attributes.map((attr, attrIndex) => (
                                <div key={attrIndex} className="attribute-row">
                                    <input
                                        type="text"
                                        placeholder="Attribute Name"
                                        value={attr.name}
                                        onChange={(e) =>
                                            handleAttributeChange(
                                                entityIndex,
                                                attrIndex,
                                                "name",
                                                e.target.value
                                            )
                                        }
                                        className="input attribute-input"
                                    />

                                    <select
                                        value={attr.type}
                                        onChange={(e) =>
                                            handleAttributeChange(
                                                entityIndex,
                                                attrIndex,
                                                "type",
                                                e.target.value
                                            )
                                        }
                                        className="dropdown"
                                    >
                                        <option value="String">String</option>
                                        <option value="int">int</option>
                                        <option value="boolean">boolean</option>
                                    </select>

                                    <label className="nullable-label">
                                        <input
                                            type="checkbox"
                                            checked={attr.nullable}
                                            onChange={(e) =>
                                                handleAttributeChange(
                                                    entityIndex,
                                                    attrIndex,
                                                    "nullable",
                                                    e
                                                )
                                            }
                                        />
                                        Nullable
                                    </label>

                                    {entity.attributes.length > 1 && (
                                        <button
                                            onClick={() =>
                                                removeAttribute(entityIndex, attrIndex)
                                            }
                                            className="button remove-attr-btn"
                                        >
                                            ✖
                                        </button>
                                    )}
                                </div>
                            ))}
                            <button
                                onClick={() => addAttribute(entityIndex)}
                                className="button add-attr-btn"
                            >
                                + Add Attribute
                            </button>
                        </div>
                    </div>
                ))}

                <button onClick={addEntity} className="button add-entity-btn">
                    + Add Another Entity
                </button>

                <button onClick={handleSubmit} className="button submit-btn">
                    Submit
                </button>
            </div>
        </div>
    );
}

export default Home;
