package uk.co.raubach.tractivity.server.pojo;

public class ParticipantMeasures
{
	private Integer participantId;
	private String participantName;
	private SimpleMeasures[] participantMeasures;

	public Integer getParticipantId()
	{
		return participantId;
	}

	public ParticipantMeasures setParticipantId(Integer participantId)
	{
		this.participantId = participantId;
		return this;
	}

	public String getParticipantName()
	{
		return participantName;
	}

	public ParticipantMeasures setParticipantName(String participantName)
	{
		this.participantName = participantName;
		return this;
	}

	public SimpleMeasures[] getParticipantMeasures()
	{
		return participantMeasures;
	}

	public ParticipantMeasures setParticipantMeasures(SimpleMeasures[] participantMeasures)
	{
		this.participantMeasures = participantMeasures;
		return this;
	}
}
